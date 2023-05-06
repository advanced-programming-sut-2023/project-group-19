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
    public void checkRegisterError(){
        RegisterMessages message = LoginController.checkErrorForRegister
                ("dsfcjd^$%","dsfc","ass",
                "sedcsdc","sdfcd","dsds");
        Assertions.assertEquals(message,RegisterMessages.INCORRECT_FORM_OF_USERNAME);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","random","ass",
                        "sedcsdc","sdfcd","dsds");
        Assertions.assertEquals(message,RegisterMessages.GET_RANDOM_PASSWORD);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","random",null,
                        "sedcsdc","sdfcd","dsds");
        Assertions.assertEquals(message,RegisterMessages.EMPTY_FIELD);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","random","df",
                        "sedcsdc","sdfcd","random");
        Assertions.assertEquals(message,RegisterMessages.GET_RANDOM_SLOGANS);

        message = LoginController.checkErrorForRegister
                ("dsfcjd","random","df",
                        "sedcsdc","sdfcd","random");
        Assertions.assertEquals(message,RegisterMessages.GET_RANDOM_SLOGANS);







    }



}