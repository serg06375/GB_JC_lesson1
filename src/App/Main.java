package App;

public class Main {

    public static void main(String[] args) {
	import lesson1.Competitors;
	import lesson1.obstacles;


        public class MainClass {
            public static void main(String[] args) {


                Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
                Obstacle[] obstacles = {new Cross(400), new Wall(2), new Water(1)};


                Team team = new Team("Winners", competitors);


                System.out.println("============created new team============");
                System.out.println(team.getTeamName());


                System.out.println("============greeting team members============");
                team.showResults();


                // new Course
                Course course = new Course(obstacles);


                System.out.println("============team goes to course============");
                course.doIt(team);


                System.out.println("============show team Results============");
                team.showResults();


                System.out.println("============team members finished course============");
                team.showMembersFinishedCourse();
            }
        }

    }
}
package lesson1.Competitors;


public abstract class Animal implements Competitor{
    String type;
    String name;
    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;
    boolean onDistance;


    public boolean isOnDistance() {
        return onDistance;
    }


    public Animal(String type, String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }


    public void run(int distance){
        if (distance <=maxRunDistance){
            System.out.println(type + " " + name + " " + " успешно справился с кросс");
        } else {
            System.out.println(type + " " + name + " " + " не смог преодолеть кросс");
            onDistance = false;
        }
    }


    public void jump(int height){
        if (height <=maxJumpHeight){
            System.out.println(type + " " + name + " " + " успешно справился с препятствием");
        } else {
            System.out.println(type + " " + name + " " + " не смог преодолеть препятствие");
            onDistance = false;
        }
    }


    public void swim(int distance){
        if (maxSwimDistance == 0){
            System.out.println(type + " " + name + " не умеет плавать");
            onDistance = false;
            return;
        }
        if (distance <=maxSwimDistance){
            System.out.println(type + " " + name + " " + " успешно проплыл дистанцию");
        } else {
            System.out.println(type + " " + name + " " + " не смог проплыть дистанцию");
            onDistance = false;
        }
    }


    public void showResult(){
        System.out.println(type + " " + name + ": " + onDistance);
    }


}
package lesson1.Competitors;


public class Cat extends Animal {
    public Cat(String name) {
        super("Кот", name, 500, 10, 0);
    }
}
package lesson1.Competitors;


public class Dog extends Animal{
    public Dog(String name) {
        super("Пес", name, 1000, 4, 20);
    }
}

package lesson1.Competitors;


public class Human implements Competitor{
    String name;
    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;
    boolean onDistance;


    public boolean isOnDistance() {
        return onDistance;
    }


    public Human(String name) {
        this.name = name;
        this.maxRunDistance = 5000;
        this.maxJumpHeight = 20;
        this.maxSwimDistance = 500;
        this.onDistance = true;
    }


    public void run(int distance){
        if (distance <=maxRunDistance){
            System.out.println(name + " " + " успешно справился с кросс");
        } else {
            System.out.println(name + " " + " не смог преодолеть кросс");
            onDistance = false;
        }
    }


    public void jump(int height){
        if (height <=maxJumpHeight){
            System.out.println(name + " " + " успешно справился с препятствием");
        } else {
            System.out.println(name + " " + " не смог преодолеть препятствие");
            onDistance = false;
        }
    }


    public void swim(int distance){
        if (distance <=maxSwimDistance){
            System.out.println(name + " " + " успешно проплыл дистанцию");
        } else {
            System.out.println(name + " " + " не смог проплыть дистанцию");
            onDistance = false;
        }
    }


    public void showResult(){
        System.out.println(name + ": " + onDistance);
    }


}
package lesson1.Competitors;


public class Team {
    String teamName;
    Competitor[] teamMembers = new Competitor[4];




    public Team(String teamName, Competitor[] teamMembers){
        this.teamName = teamName;
        this.teamMembers = teamMembers;
    }


    public String getTeamName(){
        return "Team name: "+teamName;
    }


    public Competitor[] getMembers(){
        return teamMembers;
    }


    public void showResults(){
        for(Competitor c: teamMembers){
            c.showResult();
        }
    }


    public void showMembersFinishedCourse(){
        for(Competitor c: teamMembers){
            if(c.isOnDistance())
                c.showResult();
        }
    }
}
package lesson1.Competitors;


public interface Competitor {
    void run(int distance);
    void swim(int distance);
    void jump(int height);
    boolean isOnDistance();
    void showResult();
}
package lesson1.obstacles;


        import lesson1.Competitors.Competitor;
        import lesson1.Competitors.Team;


public class Course {


    Obstacle[] obstacles;


    public Course(Obstacle[] obstacles){
        this.obstacles = obstacles;
    }


    public void doIt(Team team){
        Competitor[] teamMembers = team.getMembers();
        if(teamMembers.length > 0){
            for (Competitor c: teamMembers){
                for (Obstacle o: obstacles){
                    o.doIt(c);
                    if (!c.isOnDistance()) break;
                }
            }
        } else {
            System.out.println("There are no members in the team!");
        }
    }
}
package lesson1.obstacles;


        import lesson1.Competitors.Animal;
        import lesson1.Competitors.Competitor;


public class Cross extends Obstacle {
    private int distance;


    public Cross(int distance) {
        this.distance = distance;
    }


    @Override
    public void doIt(Competitor competitor) {
        competitor.run(distance);
    }
}
package lesson1.obstacles;


        import lesson1.Competitors.Animal;
        import lesson1.Competitors.Competitor;


public abstract class Obstacle {
    public abstract void doIt(Competitor competitor);
}

package lesson1.obstacles;


        import lesson1.Competitors.Animal;
        import lesson1.Competitors.Competitor;


public class Wall extends Obstacle {
    private int height;


    public Wall(int height) {
        this.height = height;
    }


    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}
package lesson1.obstacles;


        import lesson1.Competitors.Animal;
        import lesson1.Competitors.Competitor;


public class Water extends Obstacle {
    private int distance;


    public Water(int distance) {
        this.distance = distance;
    }


    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(distance);
    }
}

