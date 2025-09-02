(ns noortech.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

;; Application State
(defonce app-state 
  (r/atom {:form-data {:name ""
                       :email ""
                       :service ""
                       :message ""}
           :form-submitted false
           :active-service nil
           :form-errors {}
           :loading false}))

;; Utility Functions
(defn scroll-to-element [element-id]
  (when-let [element (.getElementById js/document element-id)]
    (.scrollIntoView element #js {:behavior "smooth"})))

(defn open-whatsapp []
  (.open js/window 
         "https://wa.me/27XXXXXXXXX?text=Hi%20NoorTech!%20I%27m%20interested%20in%20your%20services."
         "_blank"))

;; Form Handling
(defn handle-input-change [field-key event]
  (let [value (-> event .-target .-value)]
    (swap! app-state assoc-in [:form-data field-key] value)
    ;; Clear error when user starts typing
    (swap! app-state assoc-in [:form-errors field-key] nil)))

(defn validate-form [form-data]
  (let [errors {}]
    (cond-> errors
      (empty? (:name form-data)) (assoc :name "Name is required")
      (empty? (:email form-data)) (assoc :email "Email is required")
      (not (re-matches #".+@.+\..+" (:email form-data))) (assoc :email "Please enter a valid email")
      (empty? (:service form-data)) (assoc :service "Please select a service")
      (empty? (:message form-data)) (assoc :message "Message is required"))))

(defn handle-form-submit [event]
  (.preventDefault event)
  (let [form-data (:form-data @app-state)
        errors (validate-form form-data)]
    (if (empty? errors)
      (do
        (swap! app-state assoc :loading true)
        ;; Simulate API call
        (js/setTimeout
          (fn []
            (.log js/console "Form submission:" (clj->js form-data))
            (js/alert "Thank you for your message! We'll get back to you within 24 hours.")
            (swap! app-state assoc :form-submitted true :loading false)
            (swap! app-state assoc :form-data {:name "" :email "" :service "" :message ""}))
          1000))
      (swap! app-state assoc :form-errors errors))))

;; Service Card Interaction
(defn set-active-service [service-key]
  (swap! app-state assoc :active-service service-key))

;; Enhanced Navigation with Smooth Scrolling
(defn enhance-navigation []
  (let [nav-links (.querySelectorAll js/document ".nav-link")]
    (doseq [link (array-seq nav-links)]
      (.addEventListener link "click" 
        (fn [e]
          (.preventDefault e)
          (let [href (.getAttribute link "href")
                target-id (.substring href 1)]
            (scroll-to-element target-id)))))))

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

;; Service Card Hover Effects
(defn setup-service-interactions []
  (let [service-cards (.querySelectorAll js/document ".service-card")]
    (doseq [card (array-seq service-cards)]
      (.addEventListener card "mouseenter"
        (fn []
          (let [service-type (.getAttribute card "data-service")]
            (set-active-service service-type))))
      (.addEventListener card "mouseleave"
        (fn []
          (set-active-service nil))))))

;; Contact Form Enhancement
(defn setup-form-enhancements []
  (let [form (.getElementById js/document "contactForm")]
    (when form
      (.addEventListener form "submit" handle-form-submit))))

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
  (setup-service-interactions)
  (setup-form-enhancements)
  (lazy-load-images)
  
  ;; Log initialization
  (.log js/console "NoorTech website initialized - Strategic technology solutions for African businesses"))

(defn on-js-reload []
  (.log js/console "Figwheel reload - NoorTech development mode"))

;; Start the application when DOM is ready
(defn ^:export main []
  (if (= (.-readyState js/document) "loading")
    (.addEventListener js/document "DOMContentLoaded" init-app)
    (init-app)))

;; Auto-start
(main)