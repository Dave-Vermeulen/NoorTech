(defproject noortech-site "0.1.0-SNAPSHOT"
  :description "NoorTech Corporate Website - Illuminating business through tech"
  :url "https://noortech.app"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/clojurescript "1.11.132"]
                 [reagent "1.2.0"]
                 [cljsjs/react "18.2.0-1"]
                 [cljsjs/react-dom "18.2.0-1"]]

  :plugins [[lein-figwheel "0.5.20"]
            [lein-cljsbuild "1.1.8"]]

  :source-paths ["src"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel {:on-jsload "noortech.core/on-js-reload"
                           :open-urls ["http://localhost:3449/index.html"]}
                :compiler {:main noortech.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/noortech.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:main noortech.core
                           :output-to "resources/public/js/compiled/noortech.js"
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]
             :server-port 3449}

  :profiles {:dev {:dependencies [[binaryage/devtools "1.0.6"]
                                [figwheel-sidecar "0.5.20"]]
                 :source-paths ["src" "dev"]
                 :resource-paths ["resources"]}
           :build {:cljsbuild {:builds [{:id "min"
                                         :source-paths ["src"]
                                         :compiler {:main noortech.core
                                                    :output-to "resources/public/js/compiled/noortech.js"
                                                    :optimizations :advanced
                                                    :pretty-print false}}]}}})