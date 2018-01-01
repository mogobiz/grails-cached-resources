import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }

	logger('org.grails', ERROR, ['STDOUT'])
	logger('org.springframework', ERROR, ['STDOUT'])
	logger('org.hibernate', ERROR, ['STDOUT'])
	logger('net.sf.ehcache.hibernate', ERROR, ['STDOUT'])

    logger('grails.app', INFO, ['STDOUT'])

    logger('grails.app.controller', DEBUG, ['STDOUT'])
    logger('grails.app.service', DEBUG, ['STDOUT'])
    logger('grails.app.task', DEBUG, ['STDOUT'])
    logger('grails.app.domain', DEBUG, ['STDOUT'])
    logger('org.grails.plugin.resource', DEBUG, ['STDOUT'])
}

root(ERROR, ['STDOUT'])

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}
