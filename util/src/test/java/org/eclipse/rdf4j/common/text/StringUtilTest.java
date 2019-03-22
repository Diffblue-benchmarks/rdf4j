package org.eclipse.rdf4j.common.text;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringUtilTest {

    @Rule public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAppendN() {
        StringBuilder sb = new StringBuilder("test");

        StringUtil.appendN('0', 3, sb);
        assertEquals("test000", sb.toString());

        StringUtil.appendN('X', 2, sb);
        assertEquals("test000XX", sb.toString());

        StringUtil.appendN('Y', 0, sb);
        assertEquals("test000XX", sb.toString());
    }

    @Test
    public void testEncodeArray() {
        assertEquals("encoded_.array_.test", StringUtil.encodeArray(new String[] {"encoded", "array", "test"}));
        assertEquals("encoded_.__with___.under__scores", StringUtil.encodeArray(new String[] {"encoded", "_with_", "under_scores"}));
    }

    @Test
    public void testDecodeArray() {
        assertArrayEquals(
            new String[] {"encoded", "array", "test"},
            StringUtil.decodeArray("encoded_.array_.test"));

        assertArrayEquals(
            new String[] {"encoded", "_with_", "under_scores"},
            StringUtil.decodeArray("encoded_.__with___.under__scores"));
    }

    @Test
    public void testIsGarbageText() {
        assertFalse(StringUtil.isGarbageText("This is a Sensible Title"));
        assertFalse(StringUtil.isGarbageText("test test test test test test test test test test test test test test test test"));
        assertFalse(StringUtil.isGarbageText("onebiglongword"));

        assertTrue(StringUtil.isGarbageText("wqpoifhvqwpoifjvojwvaspdojfhvsdpoifvhaspoijfvhasvsdfjvashfvasdfv"));
        assertTrue(StringUtil.isGarbageText("g1"));
    }

    @Test
    public void testGetAllAfter() {
        assertEquals("string", StringUtil.getAllAfter("test>string", '>'));
        assertEquals("string>again", StringUtil.getAllAfter("test>string>again", '>'));
        assertEquals("no separator here", StringUtil.getAllAfter("no separator here", ':'));
    }

    @Test
    public void testGetAllBefore() {
        assertEquals("test", StringUtil.getAllBefore("test>string", '>'));
        assertEquals("test", StringUtil.getAllBefore("test>string>again", '>'));
        assertEquals("no separator here", StringUtil.getAllBefore("no separator here", ':'));
    }

    @Test
    public void testGsub() {
        assertEquals("test replace", StringUtil.gsub("string", "replace", "test string"));
        assertEquals("12replace454replace21", StringUtil.gsub("3", "replace", "123454321"));
        assertEquals("123454321", StringUtil.gsub("", "replace", "123454321"));
        assertEquals("123454321", StringUtil.gsub(null, "replace", "123454321"));

        assertNull(StringUtil.gsub("test", "other", null));
    }

    @Test
    public void testTrimDoubleQuotes() {
        assertEquals("quoted", StringUtil.trimDoubleQuotes("\"quoted\""));
        assertEquals("unquoted", StringUtil.trimDoubleQuotes("unquoted"));
        assertEquals("quotes \"middle\" test", StringUtil.trimDoubleQuotes("quotes \"middle\" test"));
    }

    @Test
    public void testConcat(){
        assertEquals("test", StringUtil.concat("test"));
        assertEquals("testother", StringUtil.concat("test", "other"));
        assertEquals("123", StringUtil.concat("1", "2", "3"));
        assertEquals("13", StringUtil.concat("1", "", "3"));
        assertEquals("", StringUtil.concat());
    }

}