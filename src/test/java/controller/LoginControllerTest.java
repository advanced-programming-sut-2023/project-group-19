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
        Assertions.assertEquals(message,RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXEPT_ALPHABETICAL);

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
    public void repeted() throws IOException {
        LoginController.register("a","a",
                "a","sedc@s.dc","a", "a","2");

        RegisterMessages message = LoginController.checkErrorForRegister
                ("a","dsfc","ass",
                        "sedcsdc","sdfcd","dsds");

        Assertions.assertEquals(message,RegisterMessages.USERNAME_REPETED);

        message = LoginController.checkErrorForRegister
                ("ajjk","G6*ffff","G6*ffff",
                        "sEdc@s.dc","sdfcd","dsds");

        Assertions.assertEquals(message,RegisterMessages.REPETED_EMAIL);



    }




}