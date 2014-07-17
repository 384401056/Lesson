/*
 * µ¥Ôª²âÊÔÊµÀı¡£
 */
package com.example.unittestandlog;

import junit.framework.Assert;
import android.test.AndroidTestCase;

public class MyTest extends AndroidTestCase {
	
	public void testSave() throws Throwable{
		int i = 4+8;
		Assert.assertEquals(12, i);
	}
	
}
