public class Tile {
    public boolean revealed;
    public int number; //9 = MINE
    public boolean flagged;

    public Tile() {
        number = 0;
        revealed = false;
        flagged = false;
    }


    public void setNumber(int number) {this.number = number;}

    public int getNumber() {return number;}

    public int toggleFlagged() {
        if(!revealed) {
            flagged = !flagged;
            if (flagged) {
                return 1;
            } else {
                return -1;
            }
        }
        else {return 0;}
    }

    public boolean check() {
        return (!revealed && !flagged);
    }

    public char drawTile() {
        if(flagged) {
            return 'F';
        } else if(!revealed) {
            return ' ';
        } else {
            // + 48 to get correct ascii index
            return (char)(this.getNumber()+48);
        }
    }

    public int reveal() {
        if(!flagged && !revealed) {
            revealed = true;
            return this.getNumber();
        } else if(!flagged) {
            //reveal around myself
            return 0;
        }
        return -1;
    }


}
