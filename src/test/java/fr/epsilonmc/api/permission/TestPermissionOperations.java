package fr.epsilonmc.api.permission;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import fr.epsilonmc.core.Core;
import fr.epsilonmc.core.Permissions;
import fr.epsilonmc.mock.bukkit.EpsilonPlayerMock;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPermissionOperations {

    private ServerMock server;
    private Core core;

    @BeforeAll
    public void setUp() {
        server = MockBukkit.mock();
        core = MockBukkit.load(Core.class);
    }

    @Test
    @DisplayName("Test if validate method is working")
    public void testValidate() {
        EpsilonPlayerMock epsilonPlayerMock = new EpsilonPlayerMock("Lucas__Lks");
        epsilonPlayerMock.addAttachment(core, Permissions.PERMISSIONS_OP_PATTERN + ".test", true);

        assertTrue(PermissionOperations.validate(epsilonPlayerMock, Permissions.PERMISSIONS_OP_PATTERN));
    }


    @AfterAll
    public void tearDown() {
        MockBukkit.unload();
    }

}
