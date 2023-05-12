package controller;
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
import view.Commands.* ;
import view.Messages.*;

class LoginControllerTest {
    @Test
    public void checkMakeUsername(){
        String text = LoginController.makeUserNameForUser("ali");
        Assertions.assertNotNull(text);

        text = LoginController.makeUserNameForUser("ali");
        Assertions.assertNotEquals(text,"ali");
    }
    @Test
    public void checkCreateRandomPassword(){
        String text = LoginController.generateRandomPassword();
        Assertions.assertNotNull(text);
    }
    @Test
    public void getRandomSloganCheck(){
        String text = LoginController.getRandomSlogan();
        Assertions.assertNotNull(text);
    }
    @Test

    public void checkRegisterErrorPassword(){
        RegisterMessages message = LoginController.checkErrorForRegister
                ("ds f cj *","dsfc","ass",
                "sedcsdc","sdfcd","dsds");
        Assertions.assertEquals(message,RegisterMessages.INCORRECT_FORM_OF_USERNAME);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","random","ass",
                        "sedcsdc","sdfcd","dsds");
        Assertions.assertEquals(message,RegisterMessages.GET_RANDOM_PASSWORD);

        message = LoginController.checkErrorForRegister
                ("dsfcjd",null,null,
                        "sedcsdc","sdfcd","dsds");
        Assertions.assertEquals(message,RegisterMessages.EMPTY_FIELD);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","random",null,
                        "sedcsdc","sdfcd","dsds");
        Assertions.assertEquals(message,RegisterMessages.GET_RANDOM_PASSWORD);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","random","df",
                        "sedcsdc","sdfcd","random");
        Assertions.assertEquals(message,RegisterMessages.GET_RANDOM_SLOGANS);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","random","df",
                        "sedcsdc","sdfcd","random");
        Assertions.assertEquals(message,RegisterMessages.GET_RANDOM_SLOGANS);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","GGHG","df",
                        "sedcsdc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","GGHGfgf","df",
                        "sedcsdc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_NUMBER);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","GGHGf76gf","df",
                        "sedcsdc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXCEPT_ALPHABETICAL);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*f","df",
                        "sedcsdc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_LENGTH);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*fjjjjj","df",
                        "sedcsdc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.NOT_SIMILAR_PASSWORD);
    }
    @Test
    public void checkkRegisterEmail(){

        RegisterMessages message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*ffff","G6*ffff",
                        "sedcsdc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.INVALID_FORM_EMAIL);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*ffff","G6*ffff",
                        "sedc@sdc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.INVALID_FORM_EMAIL);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*ffff","G6*ffff",
                        "@sdc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.INVALID_FORM_EMAIL);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*ffff","G6*ffff",
                        "s.dc","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.INVALID_FORM_EMAIL);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*ffff","G6*ffff",
                        "s.d@c","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.INVALID_FORM_EMAIL);


        message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*ffff","G6*ffff",
                        "s.d@c.","sdfcd","rvefdv");
        Assertions.assertEquals(message,RegisterMessages.INVALID_FORM_EMAIL);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","G6*ffff","G6*ffff",
                        ".@c.kj","sdfcd","rvefdv");
        Assertions.assertNotEquals(message,RegisterMessages.INVALID_FORM_EMAIL);
    }

    @Test
    public void repetedElements() throws IOException {
        LoginController.register("a","a",
                "a","sedc@s.dc","a", "a","2");

        RegisterMessages message = LoginController.checkErrorForRegister
                ("a","dsfc","ass",
                        "sedcsdc","sdfcd","dsds");

        Assertions.assertEquals(message,RegisterMessages.USERNAME_REPEATED);

        message = LoginController.checkErrorForRegister
                ("ajjk","G6*ffff","G6*ffff",
                        "sEdc@s.dc","sdfcd","dsds");

        Assertions.assertEquals(message,RegisterMessages.REPEATED_EMAIL);
    }

    @Test
    public void checkSecurityAsksUnit(){
        RegisterMessages message = LoginController.checkSecurityAsks(2,"d","t");
        Assertions.assertEquals(message,RegisterMessages.TRY_ANOTHER_SEC_ASK);

        message = LoginController.checkSecurityAsks(4,"d","t");
        Assertions.assertEquals(message,RegisterMessages.TRY_ANOTHER_SEC_ASK);

        message = LoginController.checkSecurityAsks(2,null,"t");
        Assertions.assertEquals(message,RegisterMessages.TRY_ANOTHER_SEC_ASK);

        message = LoginController.checkSecurityAsks(2,"t","t");
        Assertions.assertNotEquals(message,RegisterMessages.TRY_ANOTHER_SEC_ASK);
    }
    @Test
    public void checkLoginUnit() throws IOException {
        LoginController.register("a","a",
                "a","sedc@s.dc","a", "a","2");

        RegisterMessages message = LoginController.loginUser("c","j");
        Assertions.assertEquals(message,RegisterMessages.NOT_EXIST_USERNAME);

        message = LoginController.loginUser("a","j");
        Assertions.assertEquals(message,RegisterMessages.NOT_SIMILAR_PASSWORD);

        message = LoginController.loginUser("a","a");
        Assertions.assertEquals(message,RegisterMessages.SUCCESS);
    }
    @Test
    public void checkIsLoggedUserUnit() throws IOException {
        LoginController.register("a","a",
                "a","sedc@s.dc","a", "a","2");

        String message = LoginController.isLoggedUser("kk");
        Assertions.assertEquals(message,"this user is not exist!");

        message = LoginController.isLoggedUser("a");
        Assertions.assertEquals(message,"your username for next login is saved!");
    }

    @Test
    public void checkForgetPassword() throws IOException {
        User user = new User("a","a","a","a","s","a",5);

        RegisterMessages message = LoginController.changePassword(user,"G6*f");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_LENGTH);

        message = LoginController.changePassword(user,"6*f");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_UPPERCASE);

        message = LoginController.changePassword(user,"G*f");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_NUMBER);

        message = LoginController.changePassword(user,"G6f");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXCEPT_ALPHABETICAL);

        message = LoginController.changePassword(user,"G6*");
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE);

        message = LoginController.changePassword(user,"G6*yyyy");
        Assertions.assertEquals(message,RegisterMessages.SUCCESS);
    }






}