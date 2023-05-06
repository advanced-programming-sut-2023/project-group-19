package view;

import controller.*;
import model.User;
import view.Commands.CreateMapCommands;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateMapMenu {
    public static void run(Scanner scanner){
        int numberOfUsers = User.loginUsers.size();
        if(numberOfUsers < 2){
            System.out.println("More user must be added");
            return;
        }
        if(numberOfUsers > 8){
            while (numberOfUsers > 8){
                User.loginUsers.remove(numberOfUsers - 1);
                numberOfUsers  -- ;
            }
        }
        if(CreateMapController.numberOfEmpiers == numberOfUsers){
            System.out.println("you must have more user to continue!");
            return;
        }
        String command ;
        Matcher matcher ;
        while(true){
            command = scanner.nextLine();
            if((matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.MOVING_MAP)) != null){
                movingMap(command);
            }else if((matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_DETAIL)) != null){
                showDetail(command);
            }else if((matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.CREATE_MAP)) != null){
                System.out.println(CreateMapController.CreateMap(Integer.parseInt(matcher.group("size"))));
            }else if(CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE) != null){
                settextureOneByOne(command);
            }else if(CreateMapCommands.getMatcher(command,CreateMapCommands.CLEAR) != null){
                clearAnIndex(command);
            }else if(CreateMapCommands.getMatcher(command,CreateMapCommands.DROP_ROCK) != null){
                dropRock(command);
            }else if(CreateMapCommands.getMatcher(command,CreateMapCommands.DROP_TREE) != null){
                dropTree(command);
            }else if(CreateMapCommands.getMatcher(command,CreateMapCommands.LOCATE_CASTLE) != null){
                locateCatle(command);
            }
            else System.out.println("invalid command");
        }

    }

    private static void locateCatle(String command) {
        Matcher matcher ;
        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_X);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int x = Integer.parseInt(matcher.group("x"));
        String type ;

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_Y);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(CreateMapController.locateCatle(x,y));

    }

    public static void dropTree(String command){
        Matcher matcher ;
        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_X);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int x = Integer.parseInt(matcher.group("x"));
        String type ;

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_Y);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int y = Integer.parseInt(matcher.group("y"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_TYPE);
        if(matcher == null ){
            System.out.println("try again!");
            return;
        }
        type = matcher.group("type");
        System.out.println(CreateMapController.dropTree(x,y,type));
    }
    public static void dropRock(String command){
        Matcher matcher ;
        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_X);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int x = Integer.parseInt(matcher.group("x"));
        String type ;

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_Y);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int y = Integer.parseInt(matcher.group("y"));
        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.GET_DIRCTION);
        if(matcher == null ){
            System.out.println("try again!");
            return;
        }
        type = matcher.group("direction");
        System.out.println(CreateMapController.dropRock(x,y,type));
    }
    public static void clearAnIndex(String command){
        Matcher matcher ;
        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_X);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int x = Integer.parseInt(matcher.group("x"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_Y);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(CreateMapController.clear(x,y));
    }
    public static void settextureOneByOne(String command){
        int x = 0;
        int y = 0;
        String type = null ;
        Matcher matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_X);
        if(matcher == null){
            setTextureGroup(command);
            return;
        }
        x = Integer.parseInt(matcher.group("x"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_Y);
        if(matcher == null ){
            System.out.println("try again!");
            return;
        }
        y = Integer.parseInt(matcher.group("y"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_TYPE);
        if(matcher == null ){
            System.out.println("try again!");
            return;
        }
        type = matcher.group("type");
        System.out.println(CreateMapController.settextureOneByOne(x,y,type));
    }
    public static void setTextureGroup(String command){
        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;
        String type = null ;
        Matcher matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_X1);
        if(matcher == null){
            System.out.println("try again");
            return;
        }
        x1 = Integer.parseInt(matcher.group("x1"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_X2);
        if(matcher == null){
            System.out.println("try again");
            return;
        }
        x2 = Integer.parseInt(matcher.group("x2"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_Y1);
        if(matcher == null ){
            System.out.println("try again");
            return;
        }
        y1 = Integer.parseInt(matcher.group("y1"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_Y2);
        if(matcher == null){
            System.out.println("try again");
            return;
        }
        y2 = Integer.parseInt(matcher.group("y2"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SET_TEXTURE_TYPE);
        if(matcher == null ){
            System.out.println("try again");
            return;
        }
        type = matcher.group("type");
        System.out.println(CreateMapController.settextureGroup(x1,x2,y1,y2,type));
    }

    public static void movingMap(String command){
        System.out.println("into move map");
        int down = 0;
        int up = 0;
        int left = 0;
        int right = 0;
        Matcher matcher;
        command = command.replaceAll("map","");
        System.out.println(command);
        String regex = "(?<type>\\S+)\\s*(?<number>\\d+)?";
        matcher = Pattern.compile(regex).matcher(command);
//        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.MOVING_MAP_INTO_DIRECTION);
        while(matcher.find()){
            String type =  matcher.group("type");
            String number = matcher.group("number");
            System.out.println(type + "%%%" + number);
            //show map -x 200 -y 200
            switch (type){
                case "left" :
                    if(number != null) left = -1 * Integer.parseInt(number);
                    else left = -1 ;
                    break;
                case "right" :
                    if(number != null) right = Integer.parseInt(number);
                    else right = 1 ;
                    break;
                case "up" :
                    if(number != null) up = -1 * Integer.parseInt(number);
                    else up = -1 ;
                    break;
                case "down" :
                    if(number != null) down = Integer.parseInt(number);
                    else down = 1 ;
                    break;
            }
        }
        int deltaX = up + down ;
        int deltaY = right + left  ;
        System.out.println(ShowMapController.moveMap(deltaX,deltaY));

    }
    public static void showDetail(String command){
        Matcher matcher ;
        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_X);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int x = Integer.parseInt(matcher.group("x"));

        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.SHOW_Y);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return;
        }
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(ShowMapController.showDetail(x,y));
    }
}
