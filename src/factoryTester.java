public class factoryTester {

    public static void main(String [] args){

        Account account1 = new AccountFactory().getAccount("JOINT");
        account1.read();

        Account account2 = new AccountFactory().getAccount("CURRENT");
        account2.read();
    }
}
