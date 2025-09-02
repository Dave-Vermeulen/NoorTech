(ns noortech.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

;; Application State
(defonce app-state 
  (r/atom {:mobile-menu-open false
           :form-errors {}
           :loading false}))

;; Utility Functions
(defn scroll-to-element [element-id]
  (when-let [element (.getElementById js/document element-id)]
    (.scrollIntoView element #js {:behavior "smooth"})))

(defn open-whatsapp []
  (.open js/window 
         "https://wa.me/27717702390?text=Hi%20NoorTech!%20I%27m%20interested%20in%20your%20services."
         "_blank"))

;; Mobile Menu Toggle
(defn toggle-mobile-menu []
  (swap! app-state update :mobile-menu-open not)
  (let [nav-links (.querySelector js/document ".nav-links")]
    (if (:mobile-menu-open @app-state)
      (.add (.-classList nav-links) "active")
      (.remove (.-classList nav-links) "active"))))

;; Form Validation
(defn validate-form [form-data]
  (let [errors {}]
    (cond-> errors
      (empty? (.-value (.getElementById js/document "name"))) (assoc :name "Name is required")
      (empty? (.-value (.getElementById js/document "email"))) (assoc :email "Email is required")
      (empty? (.-value (.getElementById js/document "interest"))) (assoc :interest "Please select an interest")
      (empty? (.-value (.getElementById js/document "message"))) (assoc :message "Message is required"))))

(defn handle-form-submit [event]
  (.preventDefault event)
  (let [errors (validate-form {})]
    (if (empty? errors)
      true ; Allow form submission
      (do
        (swap! app-state assoc :form-errors errors)
        false)))) ; Prevent form submission

;; Enhanced Navigation with Smooth Scrolling
(defn enhance-navigation []
  (let [nav-links (.querySelectorAll js/document ".nav-link")]
    (doseq [link (array-seq nav-links)]
      (.addEventListener link "click" 
        (fn [e]
          (.preventDefault e)
          (let [href (.getAttribute link "href")
                target-id (.substring href 1)]
            (scroll-to-element target-id)
            ;; Close mobile menu if open
            (when (:mobile-menu-open @app-state)
              (toggle-mobile-menu))))))))

;; Intersection Observer for Navigation Highlighting
(defn setup-intersection-observer []
  (when (.-IntersectionObserver js/window)
    (let [options #js {:rootMargin "-50% 0px -50% 0px"}
          observer (js/IntersectionObserver.
                    (fn [entries]
                      (doseq [entry (array-seq entries)]
                        (when (.-isIntersecting entry)
                          (let [id (.-id (.-target entry))
                                nav-link (.querySelector js/document (str "a[href='#" id "']"))]
                            ;; Remove active class from all nav links
                            (doseq [link (array-seq (.querySelectorAll js/document ".nav-link"))]
                              (.remove (.-classList link) "active"))
                            ;; Add active class to current nav link
                            (when nav-link
                              (.add (.-classList nav-link) "active"))))))
                    options)]
      ;; Observe all main sections
      (doseq [section (array-seq (.querySelectorAll js/document "section[id]"))]
        (.observe observer section)))))

;; Parallax and Visual Effects
(defn setup-scroll-effects []
  (let [navbar (.getElementById js/document "navbar")]
    (.addEventListener js/window "scroll"
      (fn []
        (let [scroll-y (.-pageYOffset js/window)]
          ;; Navbar background opacity based on scroll
          (if (> scroll-y 50)
            (.add (.-classList navbar) "scrolled")
            (.remove (.-classList navbar) "scrolled")))))))

;; Contact Form Enhancement
(defn setup-form-enhancements []
  (let [form (.querySelector js/document ".contact-form")]
    (when form
      (.addEventListener form "submit" handle-form-submit))))

;; Mobile menu toggle
(defn setup-mobile-menu []
  (when-let [toggle (.querySelector js/document ".mobile-menu-toggle")]
    (.addEventListener toggle "click" toggle-mobile-menu)))

;; Performance optimization - lazy load images
(defn lazy-load-images []
  (when (.-IntersectionObserver js/window)
    (let [observer (js/IntersectionObserver.
                    (fn [entries]
                      (doseq [entry (array-seq entries)]
                        (when (.-isIntersecting entry)
                          (let [img (.-target entry)]
                            (.setAttribute img "src" (.getAttribute img "data-src"))
                            (.unobserve observer img)))))
                    #js {:rootMargin "200px 0px"})]
      (doseq [img (array-seq (.querySelectorAll js/document "img[data-src]"))]
        (.observe observer img)))))

;; Main App Initialization
(defn init-app []
  ;; Set up all interactive features
  (enhance-navigation)
  (setup-intersection-observer)
  (setup-scroll-effects)
  (setup-form-enhancements)
  (setup-mobile-menu)
  (lazy-load-images)
  
  ;; Log initialization
  (.log js/console "NoorTech website initialized - Healthcare technology & digital solutions"))

;; Figwheel reload hook
(defn on-js-reload []
  (.log js/console "Figwheel reload - NoorTech development mode"))

;; Start the application when DOM is ready
(defn ^:export main []
  (if (= (.-readyState js/document) "loading")
    (.addEventListener js/document "DOMContentLoaded" init-app)
    (init-app)))

;; Auto-start
(main)