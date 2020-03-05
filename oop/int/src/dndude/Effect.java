package dndude;

public interface Effect {
    void onHit(Dude effectTarget);

    void onTurnStart(Dude effectTarget);

    void onTurnEnd(Dude effectTarget);

    int requiredActionPoints();

    boolean isExpired();

    String getPihtaSonum(String nimi1, String nimi2);

    String getMoodaSonum(String nimi1, String nimi2);
}
