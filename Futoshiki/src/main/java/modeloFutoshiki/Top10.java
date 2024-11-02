package modeloFutoshiki;
import java.util.*;
/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class Top10 {
    private ArrayList<String> Top3x3;
    private ArrayList<String> Top4x4;
    private ArrayList<String> Top5x5;
    private ArrayList<String> Top6x6;
    private ArrayList<String> Top7x7;
    private ArrayList<String> Top8x8;
    private ArrayList<String> Top9x9;
    private ArrayList<String> Top10x10;

    public Top10 (ArrayList<String> lista3x3, ArrayList<String> lista4x4, ArrayList<String> lista5x5,ArrayList<String> lista6x6, ArrayList<String> lista7x7, ArrayList<String> lista8x8, ArrayList<String> lista9x9, ArrayList<String> lista10x10 ){
        setTop3x3(lista3x3);
        setTop4x4(lista4x4);
        setTop5x5(lista5x5);
        setTop6x6(lista6x6);
        setTop7x7(lista7x7);
        setTop8x8(lista8x8);
        setTop9x9(lista9x9);
        setTop10x10(lista10x10);
    }
    // <editor-fold defaultstate="collapsed" desc="Setters-Getters">
    public ArrayList<String> getTop3x3() {
        return Top3x3;
    }

    public void setTop3x3(ArrayList<String> Top3x3) {
        this.Top3x3 = Top3x3;
    }

    public ArrayList<String> getTop4x4() {
        return Top4x4;
    }

    public void setTop4x4(ArrayList<String> Top4x4) {
        this.Top4x4 = Top4x4;
    }

    public ArrayList<String> getTop5x5() {
        return Top5x5;
    }

    public void setTop5x5(ArrayList<String> Top5x5) {
        this.Top5x5 = Top5x5;
    }

    public ArrayList<String> getTop6x6() {
        return Top6x6;
    }

    public void setTop6x6(ArrayList<String> Top6x6) {
        this.Top6x6 = Top6x6;
    }

    public ArrayList<String> getTop7x7() {
        return Top7x7;
    }

    public void setTop7x7(ArrayList<String> Top7x7) {
        this.Top7x7 = Top7x7;
    }

    public ArrayList<String> getTop8x8() {
        return Top8x8;
    }

    public void setTop8x8(ArrayList<String> Top8x8) {
        this.Top8x8 = Top8x8;
    }

    public ArrayList<String> getTop9x9() {
        return Top9x9;
    }

    public void setTop9x9(ArrayList<String> Top9x9) {
        this.Top9x9 = Top9x9;
    }

    public ArrayList<String> getTop10x10() {
        return Top10x10;
    }

    public void setTop10x10(ArrayList<String> Top10x10) {
        this.Top10x10 = Top10x10;
    }
    // </editor-fold>
    
}
