package fr.epsilonmc.api.permission;

import be.seeseemelk.mockbukkit.MockBukkit;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import fr.epsilonmc.mock.core.CoreMock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPermissionOperations {

    private CoreMock core;

    @BeforeAll
    public void setUp() {
        MockBukkit.mock();
        core = MockBukkit.load(CoreMock.class);
    }

    @Test
    @DisplayName("Test if validate method is working")
    public void testValidate() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        epsilonPlayerMock.addAttachment(core, Permissions.OP_PATTERN + ".test", true);

        assertTrue(PermissionOperations.validate(epsilonPlayerMock, Permissions.OP_PATTERN));
    }


    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
