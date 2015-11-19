package ua.artcode.week8.test_ex;

/**
 * Created by serhii on 19.11.15.
 */
public class TestNP {


    public static void main(String[] args) {
        try{
            throw new NullPointerException();
        } catch (NullPointerException e){
            throw new NullPointerException();
        } catch (Exception e){
            throw new NullPointerException();
        } finally {
            throw new NullPointerException();
        }
    }
}
