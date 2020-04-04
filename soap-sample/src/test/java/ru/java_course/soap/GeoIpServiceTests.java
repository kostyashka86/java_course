package ru.java_course.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("31.185.7.208");
        assertEquals(geoIp, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
    }

    @Test
    public void testInvalidIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("31.185.7.xxx");
        assertEquals(geoIp, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
    }
}