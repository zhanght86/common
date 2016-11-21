package com.sjdf.platform.common.helper;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Create at 2013年8月14日 上午9:55:36 
 * @category 
 * @author KETQI
 */
public class ResourceBundleHelperTest {

    /**
     * Test method for {@link com.sjdf.platform.common.helper.ResourceBundleHelper#getInstance()}.
     */
    @Test
    public void testGetInstance() {
        Assert.assertNotNull(ResourceBundleHelper.getInstance());
    }

    /**
     * Test method for
     * {@link com.sjdf.platform.common.helper.ResourceBundleHelper#getText(com.sjdf.platform.common.helper.Message)}
     * .
     */
    @Test
    public void testGetTextMessage() {
        Message message = Message.createMessage("common.message", "test");
        String text = ResourceBundleHelper.getInstance().getText(message);
        Assert.assertEquals(text, "test");
    }

    /**
     * Test method for
     * {@link com.sjdf.platform.common.helper.ResourceBundleHelper#getText(java.lang.String, java.lang.Object[])}
     * .
     */
    @Test
    public void testGetTextStringObjectArray() {
        String text = ResourceBundleHelper.getInstance().getText("common.message", "test");
        Assert.assertEquals(text, "test");
    }

}
