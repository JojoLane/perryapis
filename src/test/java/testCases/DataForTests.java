package testCases;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;

public class DataForTests {

    public String [] ids = new String[10];
    public ArrayList<String> messageIds = new ArrayList<>();

    @DataProvider(name = "DataForCreateUsers")
    public Object [] [] dataForCreateUsers (){
        return new Object[] []{
                {"Harry Potter"},
                {"Ron Weasley"},
                {"Hermione Granger"},
                {"Lord Voldemort"},
                {"Sirius Black"},
                {"Kate Fleming"},
                {"Ted Hastings"},
                {"Steve Arnott"},
                {"Tommy Shelby"},
                {"Arthur Shelby"},
        };
    }

    @DataProvider(name = "DataForCreateMessages")
    public Object [] [] dataForCreateMessages (){
        return new Object[] []{
                {ids[0], ids[1], "What do you call a joke that isn't funny?", 200}, //TC001
                {ids[2], ids[7], "Do you want to know what always makes me smile?", 200}, //TC001
                {ids[4], ids[5], "What came first, the chicken or the egg?", 200}, //TC001
                {ids[4], ids[5], "Test Message", 200}, //TC001
                {ids[9], ids[7], "Hello World", 200}, //TC001
                {ids[7], ids[9], " ", 200}, //TC002
                {"", ids[6], "Hey", 400}, //TC003
                {ids[7], "", "12345", 400}, //TC004
                {"12345", ids[8], "Hey", 400}, //TC005
                {ids[9], "&&&", "12345", 400}, //TC006
                {"", "", "Hey", 400}, //TC007
                {"", "", "", 400}, //TC008

        };
    }


    @DataProvider(name = "DataForListMesssages")
    public Object [] [] dataForListMessages (){
        return new Object[] []{
                {"123", ids[1], "", 400, 0, ""}, //TC001
                {ids[2], "%%%", "", 400, 0, ""}, //TC002
                {"UUU", "235", "", 400, 0, ""}, //TC003
                {"", ids[5], "", 400, 0, ""}, //TC004
                {ids[4], "", "", 400, 0, ""}, //TC005
                {"", "", "", 400, 0, ""}, //TC006
                {ids[0], ids[1], messageIds.get(0), 200, 0,"What do you call a joke that isn't funny?" }, //TC007
                {ids[4], ids[5], messageIds.get(2), 200, 1,"What came first, the chicken or the egg?"}, //TC008
                {ids[4], ids[5], messageIds.get(3), 200, 0,"Test Message"}, //TC009
        };
    }

    @DataProvider(name = "DataForGetMesssage")
    public Object [] [] dataForGetMessage (){
        return new Object[] []{
                {"2545", 404, ""}, //TC001
                {messageIds.get(1), 200, "Do you want to know what always makes me smile?"}, //TC002
                {"", 404, ""}, //TC003

        };
    }

    @DataProvider(name = "DataForUpdateMesssage")
    public Object [] [] dataForUpdateMessage (){
        return new Object[] []{
                {"2545","Updated Message", 400}, //TC001
                {messageIds.get(1),"Updated Message",200}, //TC002
                {"", "Updated Message", 400}, //TC003
                {messageIds.get(1),"Exiting Message",200}, //TC002

        };
    }

    @DataProvider(name = "DataForDeleteMesssage")
    public Object [] [] dataForDeleteMessage (){
        return new Object[] []{
                {"2545","", "", 404}, //TC001
                {messageIds.get(1),ids[2], ids[7], 204},//TC002
                {"", "", "", 404 }, //TC003

        };
    }


}
