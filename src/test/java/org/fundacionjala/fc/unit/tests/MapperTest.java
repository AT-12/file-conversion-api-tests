package org.fundacionjala.fc.unit.tests;

import junit.framework.TestCase;
import org.fundacionjala.fc.utils.Mapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class MapperTest extends TestCase {

    /**
     * Test get User value from gradle.properties.
     */
    @Test
    public void endPointWithOneParamTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "48943230192");
        String originalEndPoint = "enpoint/{id}/otherendpoint";
        String expected = "enpoint/48943230192/otherendpoint";
        Mapper mapper = Mapper.getInstance();
        String actual = mapper.mapValue(originalEndPoint, map);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get User value from gradle.properties.
     */
    @Test
    public void endPointWithTwoParamsTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "48943230192");
        map.put("ONE_ID", "123456");
        String originalEndPoint = "enpoint/{id}/otherendpoint/{ONE_ID}";
        String expected = "enpoint/48943230192/otherendpoint/123456";
        Mapper mapper = Mapper.getInstance();
        String actual = mapper.mapValue(originalEndPoint, map);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test get User value from gradle.properties.
     */
    @Test
    public void endPointWithoutParamsTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "48943230192");
        map.put("ONE_ID", "123456");
        String originalEndPoint = "enpoint/fixed/otherendpoint";
        String expected = "enpoint/fixed/otherendpoint";
        Mapper mapper = Mapper.getInstance();
        String actual = mapper.mapValue(originalEndPoint, map);
        Assert.assertEquals(actual, expected);
    }
}
