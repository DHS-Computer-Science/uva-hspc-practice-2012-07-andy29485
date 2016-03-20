//Andriy Zasyppkin
//2016-03-20
//Practice 2012 - 07: Back and Forth

import java.util.*;

class Person {
  public int x, y, dir;
  public String name;

  Person (int x, int y, int dir, String name) {
    this.x = x;
    this.x = y;
    this.dir = dir;
    this.name = name;
  }
}

public class Main {
  public static ArrayList<Person> kids_in_spot(int x, int y,
                                               ArrayList<Person> kids) {
    ArrayList<Person> tmp = new ArrayList<Person>();
    for(Person k : kids) {
      if(k.x == x && k.y == y) {
        tmp.add(k);
      }
    }
    return tmp;
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    int nCases = scan.nextInt();scan.nextLine();
    //System.err.println(scan.nextLine());

    for(int nCase=1; nCase<=nCases; nCase++) {
      ArrayList<Person> kids = new ArrayList<Person>();
      //System.err.println(scan.nextLine());
      int X = scan.nextInt();
      int Y = scan.nextInt();
      int nKids = scan.nextInt(); scan.nextLine();

      for(int i=0; i<nKids; i++) {
        String[] kid = scan.nextLine().split("\\s+");
        kids.add(new Person(Integer.valueOf(kid[1]), Integer.valueOf(kid[2]),
                            "NESW".indexOf(kid[3]), kid[0]));
      }
      int nMoves = scan.nextInt();

      Person it = kids.get(0);

      for(int i=0; i<nMoves; i++) {
        //System.err.println(i);
        for(Person k : kids) {
          if(k.dir == 0) {
            if(k.y == Y-1) {
              k.y--;
              k.dir+=2;
              k.dir%=4;
            }
            else
              k.y++;
          }
          else if(k.dir == 1) {
            if(k.x == X-1) {
              k.x--;
              k.dir+=2;
              k.dir%=4;
            }
            else
              k.x++;
          }
          else if(k.dir == 2) {
            if(k.y == 0) {
              k.y++;
              k.dir+=2;
              k.dir%=4;
            }
            else
              k.y--;
          }
          else {
            if(k.x == 0) {
              k.x++;
              k.dir+=2;
              k.dir%=4;
            }
            else
              k.x--;
          }
        }
        for(int j=0; j<X; j++) {
          for(int k=0; k<Y; k++) {
            List<Person> inLoc = kids_in_spot(j, k, kids);
            if(inLoc.size() > 2) {
              for(Person l : inLoc) {
                l.dir+=2;
                l.dir%=4;
                if(l.dir%2 == 0)
                  l.y += l.dir == 0 ? 1 : -1;
                else
                  l.x += l.dir == 1 ? 1 : -1;
              }
            }
            else if(inLoc.size() == 2) {
              if(inLoc.get(0).name.length() < inLoc.get(1).name.length()) {
                inLoc.get(0).dir = (inLoc.get(1).dir+2)%4;
              }
              else {
                inLoc.get(1).dir = (inLoc.get(0).dir+2)%4;
              }
              for(Person z : inLoc) {
                if(z.dir%2 == 0)
                  z.y += z.dir == 0 ? 1 : -1;
                else
                  z.x += z.dir == 1 ? 1 : -1;
              }
            }
          }
        }
      }
      Person c = kids.get(1);
      double dist = Math.sqrt((it.x-c.x)*(it.x-c.x)+(it.y-c.y)*(it.y-c.y));
      for(int j=2; j<nKids; j++) {
        Person tp = kids.get(j);
        double td =Math.sqrt((it.x-tp.x)*(it.x-tp.x)+(it.y-tp.y)*(it.y-tp.y));
        if(dist > td) {
          c = tp;
          dist = td;
        }
        else if(dist == td && c.name.length() > tp.name.length()) {
          c = tp;
          dist = td;
        }
      }
      System.out.printf("Case %d: %s\n", nCase, c.name);
    }

    System.exit(0);
  }
}
