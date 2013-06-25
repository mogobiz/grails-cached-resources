package org.grails.plugin.cacheresources

import com.grailsrocks.cacheheaders.CacheHeadersService
import grails.test.mixin.TestFor
import org.grails.plugin.cachedresources.HashAndCacheResourceMapper
import org.grails.plugin.resource.ResourceMeta
import org.junit.After
import org.junit.Before
import org.junit.Test

@TestFor(CacheHeadersService)
class HashAndCacheResourceMapperTests {
    MockResourceService resourceService
    HashAndCacheResourceMapper mapper

    @Before
    public void setUp() {
        grailsApplication.config.grails.cached.resources.excludes = [
                "*.png"
        ]

        resourceService = new MockResourceService()
        mapper = new HashAndCacheResourceMapper(grailsApplication: grailsApplication, resourceService: resourceService)
    }

    @After
    public void tearDown() {
    }

    @Test
    void testExclusionFilters() {
        File pngFile = File.createTempFile("boring-", ".png", resourceService.workDir)
        File gifFile = File.createTempFile("boring-", ".gif", resourceService.workDir)

        pngFile << "blahblahblah".getBytes("UTF-8")
        pngFile.deleteOnExit()

        gifFile << "blarblarblar".getBytes("UTF-8")
        gifFile.deleteOnExit()

        ResourceMeta pngFileMeta = new ResourceMeta(
                sourceUrl: pngFile.getName(),
                sourceUrlExtension: "png",
                processedFile: pngFile,
                workDir: resourceService.workDir
        )

        ResourceMeta gifFileMeta = new ResourceMeta(
                sourceUrl: gifFile.getName(),
                sourceUrlExtension: "gif",
                processedFile: gifFile,
                workDir: resourceService.workDir
        )

        mapper.map(pngFileMeta, null)
        mapper.map(gifFileMeta, null)

        assertEquals("PNG should not have been renamed", pngFileMeta.processedFile.name, pngFile.name)
        assertFalse("GIF should have been renamed", gifFileMeta.processedFile.name == gifFile.name)

        pngFileMeta.processedFile.delete()
        gifFileMeta.processedFile.delete()
    }

    class MockResourceService {
        def workDir = new File(System.getProperty("java.io.tmpdir"))

        def getConfigParamOrDefault(param, defaultValue) {
            return defaultValue
        }
    }
}
