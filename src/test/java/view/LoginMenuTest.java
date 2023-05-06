package view;

import controller.LoginController;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import view.Commands.LoginAndRegisterCommands;

import java.io.IOException;
import java.util.regex.Matcher;


public class LoginMenuTest {
    @ExtendWith(MockitoExtension.class)
    @Test

    public void test1() throws IOException {
        User user1 = new User("d", "a", "d", "a", "s", "a", 4);
        User user2 = new User("d", "a", "d", "a", "s", "a", 4);
        Assertions.assertEquals(user1, user2);
    }
    @Test
    public void checkUsername(){
        String command = "-u arminMahmudiezhad";
        Matcher checkUsername = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_USERNAME_CHECK);
        Assertions.assertNotNull(checkUsername);

        command = "-u \" ar mi nMa hm udi ez  had\" ";
        checkUsername = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_USERNAME_CHECK);
        Assertions.assertNotNull(checkUsername);

        command = "-u \" ar mi nMa hm udi e&&&*z  had\" ";
        checkUsername = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_USERNAME_CHECK);
        Assertions.assertNotNull(checkUsername);
    }
    @Test
    public void checkPassword(){
        String command = "-p arminMahmudiezhad";
        Matcher checkPassword = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_PASSWORD_CHECK);
        Assertions.assertNotNull(checkPassword);

        command = "-p \" ar mi nMa hm udi ezf&*&*%%^898rdf  had\" rgvtrfgvtrf";
        checkPassword = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_PASSWORD_CHECK);
        Assertions.assertNotNull(checkPassword);

        command = "-p \" ar y565trfgmi nMa hm udi e&&&*z  had\"  5trgvtrfgvrtfg";
        checkPassword = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_PASSWORD_CHECK);
        Assertions.assertNotNull(checkPassword);
    }
    @Test
    public void checkEmail(){
        String command = "-e arminMahmu@diezha.d";
        Matcher checkEmail = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_EMAIL_CHECK);
        Assertions.assertNotNull(checkEmail);

        command = "--email FJFKJ78@HJD.CS rgvtrfgvtrf";
        checkEmail = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_EMAIL_CHECK);
        Assertions.assertNotNull(checkEmail);

    }
    @Test
    public void checkNickName(){
        String command = "-n jeneral ";
        Matcher checkNickName = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_NICKNAME_CHECK);
        Assertions.assertNotNull(checkNickName);

        command = "-n \"cdscb dhc  fcewdsc hesd cesfcesdc erdcerhverd verfvber\" ";
        checkNickName = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_NICKNAME_CHECK);
        Assertions.assertNotNull(checkNickName);
    }
    @Test
    public void checkSlogan(){
        String command = "-s jenjnjeral ";
        Matcher checkSlogan = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_SLOGAN_CHECK);
        Assertions.assertNotNull(checkSlogan);

        command = "-s  ";
        checkSlogan = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_SLOGAN_CHECK);
        Assertions.assertNull(checkSlogan);
    }
    @Test
    public void CheckTestCase(){

    }

}

