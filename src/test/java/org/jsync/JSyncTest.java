package org.jsync;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: benoit
 * Date: 20/10/12
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class JSyncTest {
    @Test
    public void synchronizeNonExistantSource() {
        try {
            new JSync("nonexists", "").synchronize();
            fail("Synchronization of non existant source should fail.");
        } catch (IOException e) {
            assertEquals("nonexists doesn't exists.", e.getMessage());
        }
    }
}
