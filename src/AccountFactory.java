public class AccountFactory {

    public Account getAccount(String AccountType){
        if(AccountType == null){
            return null;


        }

        if(AccountType.equalsIgnoreCase("Current")){
            return new Current();

        }

        else if(AccountType.equalsIgnoreCase("Joint")){
            return new Joint();
        }

        return null;
    }
}
