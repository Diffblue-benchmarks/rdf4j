package org.eclipse.rdf4j.common.io;

import org.junit.Assert;
import org.junit.Test;

public class ByteArrayUtilTest {
    @Test
    public void testCompareRegion() {
        Assert.assertEquals(0, ByteArrayUtil.compareRegion(new byte[] {2, 32}, 3, new byte[] {-2, -33}, 3, -2_147_483_648));
        Assert.assertEquals(3, ByteArrayUtil.compareRegion(new byte[] {32, 40, 0, 104, 8, 4, 32, 44}, 1, new byte[] {16, 16, 1, 1, 37, 0, 101, 8, 36}, 4, 8));
        Assert.assertEquals(0, ByteArrayUtil.compareRegion(new byte[] {45, -128, 4, 101, 5, 4, 32, 33, 64, -128, 37, 0, 36, 1}, 10, new byte[] {16, 16, 1, 1, 33, 2, 101, 8, 36, 2, -91, 1, 32, -91, 4, 37, 0}, 15, 1));
    }

    @Test
    public void testFind() {
        Assert.assertEquals(0, ByteArrayUtil.find(new byte[] {}, -2_147_483_647, -2_147_483_648, new byte[] {}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {}, -2_147_483_648, -2_147_483_648, new byte[] {0}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {}, -2_147_483_647, -2_147_483_646, new byte[] {0, 32}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {}, -2_147_483_648, -2_147_483_648, new byte[] {1, 0}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {}, -2_147_483_640, -2_147_483_623, new byte[] {7, 7, 7, 7, 7, 18, -121, -125, 5, 7, -123, -106, -121, -109, -63, -46, -121}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {}, -2_147_483_648, -2_147_483_628, new byte[] {16, 20, 20, 17, 20, -107, 17, 17, 20, 21, 20, 16, 52, 29, 52, 20, 16, -111, 21, 23, 1}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {}, -2_147_483_638, 6, 2));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {}, 0, -2_147_483_648, new byte[] {0}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {1}, -2_147_483_648, -2_147_483_648, new byte[] {0}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {0}, 0, 3, 64));
        Assert.assertEquals(0, ByteArrayUtil.find(new byte[] {0}, 0, 3, 0));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {-1, -9, -41}, -2_147_483_622, 2, new byte[] {-9, -2}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {0, 4, 8, 64, 4}, 15, -2_147_483_647, new byte[] {0, 32, 33}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {0, 1, 64, 1, 1, 1, 64, -128}, 9, -2_147_483_648, new byte[] {1, 0}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {0, 4, 8, 64, 4, 2, 1, 2, 1}, -2_147_418_100, -2_147_418_092, new byte[] {0, 32, 33, 0, 34, 36, 34, 34}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {1, 1, 1, 1, 2, 2, 4, 1, 8, 8}, (byte) 10, (byte) 14, (byte) 2));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {68, 76, 68, 70, 69, 70, 68, 70, 68, 69, 70}, 15, -2_147_483_638, new byte[] {68, 68, 108, 79, 76, 78, 75, 78, 102, 79, 79, 77, 77, 77, -49, 78}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {-1, -69, -41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50}, 14, -2_147_483_648, new byte[] {-69, -2}));
        Assert.assertEquals(4, ByteArrayUtil.find(new byte[] {127, 87, -41, 0, 127, 127, 127, 0, -21, 0, -43, 0, 0, 0, 50}, 4, 7, new byte[] {127, 127}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {127, -41, -41, 0, 126, 126, 126, 0, -21, 0, -43, 0, 0, 0, 50}, 4, 10, new byte[] {126, 127, -41, 86, -91}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {-57, -61, -57, -61, -25, -61, -62, 2, 1, 1, 1, 5, -57, 1, -58, -58, 0}, -1_711_276_005, -1_711_275_949, new byte[] {-61, -57, -61, 3, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 16, 0, 1}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {77, 76, 77, -52, 76, 77, 70, 77, 76, 76, 78, -123, -52, 126, 77, 79, 15}, -2_147_483_648, 5, new byte[] {76, 77, -52, -50}));
        Assert.assertEquals(0, ByteArrayUtil.find(new byte[] {0, 0, 0, 1, 32, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 32, 2, 1}, -2_147_483_647, 18, new byte[] {0}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 32, 0, 0}, 16, 18, new byte[] {0}));
        Assert.assertEquals(8, ByteArrayUtil.find(new byte[] {1, -124, -125, -125, -121, -124, -125, -124, -125, -125, -125, -123, -125, -121, -124, -124, 71, -59, -124, 6, 0, 0, 0, 0}, 7, 11, new byte[] {-125}));
        Assert.assertEquals(4, ByteArrayUtil.find(new byte[] {84, -36, 100, 70, -77, -77, -77, -77, 90, 89, -78, 91, -80, -103, -77, 49, 111, 79, -112, 57, -8, 127, 8, 4, 17, -71, -78}, 4, 26, new byte[] {-77}));
        Assert.assertEquals(0, ByteArrayUtil.find(new byte[] {16, 16, 16, 4, 16, -116, 4, 18, -125, 19, 16, 4, 16, 4, 4, 22, -106, -60, 4, 4, -125, -111, 4, 80, 80, 2, 16, 21, 5, -126}, -2_147_483_648, 30, new byte[] {16, 16, 16}));
        Assert.assertEquals(-1, ByteArrayUtil.find(new byte[] {3, 2, 0, 3, 0, 0, 3, 16, 18, 16, 0, -128, 3, 0, 3, 0, 0, 10, 2, -125, 7, 1, 64, 9, 2, 7, 6, 3, 2, 0}, 12, 24, new byte[] {0, 3, 1, 0, 0, 3, 3, 7, 16, 4}));
    }

    @Test
    public void testRegionMatches() {
        Assert.assertEquals(true, ByteArrayUtil.regionMatches(new byte[] {}, new byte[] {1, 0}, 1));
        Assert.assertEquals(true, ByteArrayUtil.regionMatches(new byte[] {0}, new byte[] {16, 0, 0, 4, 16, 2, 2, 8}, 1));
        Assert.assertEquals(false, ByteArrayUtil.regionMatches(new byte[] {32, 0}, new byte[] {1, 0}, 1));
    }

    @Test
    public void testMatchesPattern() {
        Assert.assertEquals(true, ByteArrayUtil.matchesPattern(new byte[] {}, new byte[] {0, 32}, new byte[] {0, 16}));
        Assert.assertEquals(true, ByteArrayUtil.matchesPattern(new byte[] {4}, new byte[] {0}, new byte[] {2}));
        Assert.assertEquals(false, ByteArrayUtil.matchesPattern(new byte[] {2, 0}, new byte[] {2, 0}, new byte[] {0, 16}));
    }
}
