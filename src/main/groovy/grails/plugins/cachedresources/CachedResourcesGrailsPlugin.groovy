package grails.plugins.cachedresources

class CachedResourcesGrailsPlugin {

    def grailsVersion = "3.0 > *"
    // the other plugins this plugin depends on
    def loadAfter = ['resources']
    
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
            "src/main/webapp/css/**/*.*",
            "src/main/webapp/js/**/*.*"
    ]

    def developers = [ [name: "Marc Palmer", email: "marc@grailsrocks.com"] ]
    
    def title = "Cached Resources"
    def description = '''\\
Makes static resources browser-cacheable with unique filenames based on their content
'''

    def documentation = "http://grails.org/plugin/cached-resources"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }
    
    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
