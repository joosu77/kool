/**
 * Collection for holding Person objects.<br>
 * Provides the following guarantees:<br>
 * 1) Elements are guaranteed to be in ascending order, sorted by their ID code value.<br>
 * 2) Elements are guaranteed to have unique ID code values.<br>
 * <p>
 * Uses an underlying array for storing the elements. <br>
 * The object guarantees to not use more than twice the required array size.<br>
 * For example, if currently 10 persons are stored, then the underlying array size might range from 10 to 20, but will not be larger.
 */
public class SortedUniquePersonList {
    Person[] personList = new Person[0];

    /**
     * Returns reference to object at the given index. Checks that the given index is in bounds of the underlying array, returns null if it isn't.
     *
     * @param index Index at which the object is searched.
     * @return Person object at the given index, or null if the index is out of bounds.
     */
    public Person getElementAt(int index) {
        if (index < 0 || index >= this.personList.length) {
            return null;
        } else {
            return personList[index];
        }
    }

    /**
     * Returns the index of the object with the given ID code. If an object with the given ID code is not present, returns -1.
     *
     * @param idCode ID code that is searched.
     * @return Index at which the Person object with the given ID code can be found, or -1 if no such ID code is present.
     */
    public int indexOf(int idCode) {
        if (personList.length == 0) {
            return -1;
        }
        int index = findId(0, this.personList.length - 1, idCode);
        return (idCode == personList[index].getIdCode()) ? (index) : (-1);
    }

    /**
     * Binary searches the personList for an idCode
     *
     * @param limit1  Start of the interval to be searched
     * @param limit2  End of the interval to be searched
     * @param idValue ID code that is searched
     * @return index of the ID code or index of the closest ID if that ID does not exist
     */
    private int findId(int limit1, int limit2, int idValue) {
        if (limit1 == limit2) {
            return limit1;
        }
        int mediumIndex = (limit1 + limit2) / 2;
        if (personList[mediumIndex].getIdCode() < idValue) {
            if (mediumIndex == limit1) {
                return limit2;
            } else {
                return findId(mediumIndex, limit2, idValue);
            }
        } else if (personList[mediumIndex].getIdCode() == idValue) {
            return mediumIndex;
        } else {
            return findId(limit1, mediumIndex, idValue);
        }
    }

    /**
     * Attempts to add the person to the collection, but only if no person with the same ID code is already present.<br>
     * If an element is added, it is inserted to the correct position according to their ID code. Also, the index of all subsequent elements is then increased.<br>
     * If a Person object with the same ID code is already present, does nothing.
     *
     * @param person Person object to be added.
     * @return true if person was added to the collection, false otherwise.
     */
    public boolean add(Person person) {
        if (personList.length == 0) {
            personList = new Person[1];
            personList[0] = person;
        }
        int id = findId(0, personList.length - 1, person.getIdCode());
        if (person.getIdCode() == personList[id].getIdCode()) {
            return false;
        } else {
            Person[] temparray = new Person[personList.length + 1];
            if (person.getIdCode() < personList[id].getIdCode()) {
                for (int i = 0; i < temparray.length; i++) {
                    if (i < id) {
                        temparray[i] = personList[i];
                    } else if (i == id) {
                        temparray[i] = person;
                    } else {
                        temparray[i] = personList[i - 1];
                    }

                }
            } else {
                for (int i = 0; i < temparray.length; i++) {
                    if (i < id + 1) {
                        temparray[i] = personList[i];
                    } else if (i == id + 1) {
                        temparray[i] = person;
                    } else {
                        temparray[i] = personList[i - 1];
                    }
                }
            }
            personList = new Person[temparray.length];
            for (int i = 0; i < temparray.length; i++) {
                personList[i] = temparray[i];
            }
            return true;
        }
    }

    /**
     * Attempts to remove the person with the given ID code from the collection. Does nothing if no Person object with the given ID code is present.<br>
     * In the case of a successful removal of an object, decreases the index of all subsequent elements.
     *
     * @param idCode ID code that is searched.
     * @return true if the person with the given ID code was removed, false otherwise.
     */
    public boolean removeElement(int idCode) {
        int index = indexOf(idCode);
        if (index == -1) {
            return false;
        } else {
            Person[] temparray = new Person[personList.length - 1];
            for (int i = 0; i < temparray.length; i++) {
                if (i < index) {
                    temparray[i] = personList[i];
                } else if (i >= index) {
                    temparray[i] = personList[i + 1];
                }
            }
            personList = new Person[temparray.length];
            for (int i = 0; i < temparray.length; i++) {
                personList[i] = temparray[i];
            }
            return true;
        }
    }

    /**
     * Calculates and returns the size of the collection.
     *
     * @return Number of elements in the collection.
     */
    public int size() {
        return personList.length;
    }
}