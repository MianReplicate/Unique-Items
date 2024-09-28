package mc.mian.uniqueitems.api;

public interface UniqueItem {
    boolean uniqueItems$canMakeNewStack();
    boolean uniqueItems$isEditable();
    int uniqueItems$getUniqueness();
    void uniqueItems$setUniqueness(int uniqueness);
    void uniqueItems$setSide(boolean clientSided);
    boolean uniqueItems$isClientSide();
    boolean uniqueItems$isUniqueInList();
}
