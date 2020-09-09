package pages;

public class Mouse extends Animal{
    public void playDead () {
        System.out.println(classAndName() + " looks dead, but is not!");
    }
    public void speak (){
        System.out.println("Brr brr");
    }
    public void itchAndScratch () {
        System.out.println("SWOOSH, Swoosh, swooooosh-sh-sh-sh");
    }
    int tailLength= 6;
}
