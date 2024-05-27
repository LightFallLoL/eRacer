package org.milaifontanals.projecte.Model.Response;

import org.milaifontanals.projecte.Model.Cursa;

import java.util.List;

public class CursaResponse {
    private List<Cursa> curses;

    public List<Cursa> getCurses() {
        return curses;
    }

    public void setCurses(List<Cursa> curses) {
        this.curses = curses;
    }
}