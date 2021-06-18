package edu.cs.algo;


import java.util.*;

/**
 * Implementation of the Quine-McCluskey alogorithm developed by Willard V. Quine and Edward J. McCluskey.
 * This algorithm is a method used for minimization of Boolean functions.
 * The algorithm expects the input of the Boolean function in the form of its minterms,
 * i.e. complete conjunctions of the variable evaluations for which the function evaluates to 1.
 * For this implementation the minterms have to be translated in to a table of evalutation,
 * such that its an ArrayList of ArrayLists which contain Integer-objects.
 * True = 1 , False = 0
 *
 * @author Leon Schwarzer
 *
 */
public class QMCAlgorithm {

    /**
     * Returns the Prime Implicants for the given table of evaluations related to a Disjunctive Normal Function (DNF).
     * In the {@code evaluationsTable} each {@code ArrayList<Integer>} can be perceived as a minterm of the DNF.
     *
     * @param evaluationsTable
     * @return Set of Prime Implicants
     */
    public static HashSet<ArrayList<Integer>> getPrimeImplicants(ArrayList<ArrayList<Integer>> evaluationsTable){

        int numberOfColumns = evaluationsTable.get(0).size();
        // Set of Prime Implicants (implicants which cannot be shortened further)
        HashSet<ArrayList<Integer>> primeImplicants = new HashSet<>();

        while ( !(evaluationsTable.isEmpty()) ) {

            boolean[] compared = new boolean[evaluationsTable.size()];
            ArrayList<ArrayList<Integer>> newEvalTable = new ArrayList<>();

            // One iteration of the Quine-McCluskey-Algorithm
            int i = 0;
            for (ArrayList<Integer> eval1 : evaluationsTable) {

                // Hold one evaluation and compare it with all following evaluations
                int j = 0;
                for (ArrayList<Integer> eval2 : evaluationsTable) {

                    Integer diffPosition = null;
                    /*
                     * Compare the two evaluations
                     * If they differ from each other in exactly one {@code diffPosition} in the way that
                     * one evaluation equals 1 and the other equals 0, they can be shortened with each other.
                     * If they differ in more than one position or in any other way than the above,
                     * discard the previously set {@code diffPosition} and {@code break} the comparison.
                     */
                    for (int k = 0; k < numberOfColumns; k++) {
                        if ((eval1.get(k) == 1 && eval2.get(k) ==0) || (eval1.get(k) == 0 && eval2.get(k) == 1)) {
                            if (diffPosition == null)
                                diffPosition = k;
                            else {
                                diffPosition = null;
                                break;
                            }
                        }
                        else if (!eval1.get(k).equals(eval2.get(k))) {
                            diffPosition = null;
                            break;
                        }
                    }
                    /*
                     * If {@code diffPosition} is set, the evaluations can be shortened.
                     * Copy the evaluations except for the entry at position {@code diffPosition}.
                     * This entry is set to -1 as it's not important for the evaluation anymore.
                     * (-1 = "don't care")
                     * As the two evaluations were compared successfully
                     * their entry in the field {@code compared} is set to true.
                     */
                    if (diffPosition != null) {
                        ArrayList<Integer> newEval = new ArrayList<>(numberOfColumns);
                        for (int k = 0; k < numberOfColumns; k++) {
                            if (k == diffPosition)
                                newEval.add(-1);
                            else
                                newEval.add(eval1.get(k));
                        }
                        newEvalTable.add(newEval);
                        compared[i] = true;
                        compared[j] = true;
                    }
                    j++;
                }
                i++;
            }
            /*
             * If an evaluation hasn't been compared successfully in the last iteration,
             * it can't be shortened further so it has to be a Prime Implicant
             * and is added to the set of {@code primeImplicants}.
             */
            int k = 0;
            for (ArrayList<Integer> eval : evaluationsTable) {
                if (!compared[k]) {
                    primeImplicants.add(eval);
                }
                k++;
            }
            evaluationsTable = newEvalTable;
        }
        return primeImplicants;
    }

    /**
     * Generates the Prime Implicants Chart for the given set of prime implicants.
     * First all possible {@code interpretations} are generated, then it is checked,
     * if the interpretations satisfy the prime implicants. The {@code satisfactions} are put in the
     * {@code piChart} as value to the currently checked prime implicant.
     *
     * @param primeImplicants
     * @return  prime implicants chart
     */
    public static Map<ArrayList<Integer>, ArrayList<Boolean>> getPIChart(HashSet<ArrayList<Integer>> primeImplicants){
        HashMap<ArrayList<Integer>, ArrayList<Boolean>> piChart = new HashMap<>(primeImplicants.size());
        int size = primeImplicants.iterator().next().size();
        ArrayList<ArrayList<Integer>> interpretations = new ArrayList<>((int) Math.pow(2.0, size));
        for (int i = 0; i < Math.pow(2.0, size); i++) {
            ArrayList<Integer> interpretation = new ArrayList<>(size);
            for (int j = size - 1; j >= 0; j--) {
                if (((1 << j) & i) != 0){
                    interpretation.add(1);
                }
                else {
                    interpretation.add(0);
                }
            }
            interpretations.add(interpretation);
        }
        for (ArrayList<Integer> primeImplicant : primeImplicants){
            ArrayList<Boolean> satisfactions = new ArrayList<>(primeImplicant.size());
            for (ArrayList<Integer> interpretation : interpretations){
                boolean satisfies = true;
                for (int i = 0; i < interpretation.size(); i++) {
                    if ((primeImplicant.get(i) != -1) && (!primeImplicant.get(i).equals(interpretation.get(i)))){
                        satisfies = false;
                        break;
                    }
                }
                satisfactions.add(satisfies);
            }
            piChart.put(primeImplicant, satisfactions);
        }
        return piChart;
    }

    /**
     * Returns the final implicants for the given prime implicants chart {@code piTable}.
     *
     * @param piTable
     * @return  set of the final implicants
     */
    public static HashSet<ArrayList<Integer>> getFinalImplicants(Map<ArrayList<Integer>, ArrayList<Boolean>> piTable){

        // Set of final Implicants for syntactic formula
        HashSet<ArrayList<Integer>> finalImplicants = new HashSet<>();

        // Remove all interpretations from the Prime Implicants Chart which don't satisfy any of the Prime Implicants
        int interpretationsNumber = piTable.get(piTable.keySet().iterator().next()).size();
        ArrayList<Integer> noTrues = new ArrayList<>();
        for (int i = 0; i < interpretationsNumber; i++) {
            boolean containsTrue = false;
            for (ArrayList<Integer> primeImplicant : piTable.keySet()){
                if (piTable.get(primeImplicant).get(i)){
                    containsTrue = true;
                    break;
                }
            }
            if (!containsTrue){
                noTrues.add(i);
            }
        }
        Collections.sort(noTrues);
        Collections.reverse(noTrues);
        for (int index : noTrues) {
            for (ArrayList<Integer> primeImplicant : piTable.keySet()) {
                piTable.get(primeImplicant).remove(index);
            }
            interpretationsNumber--;
        }

        /*
         * Look for Prime Implicants with unique "trues" in the prime implicant chart
         * and add them to the set of final Implicants as they're essential for the formula.
         */
        for (ArrayList<Integer> primeImp1 : piTable.keySet()){
            ArrayList<Boolean> satisfactions = piTable.get(primeImp1);
            for (int i = 0; i < satisfactions.size(); i++){
                boolean uniqueTrue = true;
                if (!satisfactions.get(i))
                    continue;
                else{
                    /*
                     * If one of the following Implicants also satisfies the currently compared interpretation,
                     * the Implicant primeImp1 doesn't have a unique "true" for this interpretation and the comparison is stopped.
                     */
                    for (ArrayList<Integer> primeImp2 : piTable.keySet()){
                        if (primeImp1 == primeImp2)
                            continue;
                        if (piTable.get(primeImp2).get(i)) {
                            uniqueTrue = false;
                            break;
                        }
                    }
                }
                if (uniqueTrue)
                    finalImplicants.add(primeImp1);
            }
        }
        /*
         * Identify all interpretations, which satisfy at least one of the final implicants.
         */
        ArrayList<Integer> covered = new ArrayList<>();
        for (int i = 0; i < interpretationsNumber; i++) {
            for (ArrayList<Integer> finalImplicant : finalImplicants){
                if (piTable.get(finalImplicant).get(i)){
                    covered.add(i);
                    break;
                }
            }
        }

        /*
         * Remove all final implicants from the prime implicants chart as they don't have to be compared anymore.
         */
        for (ArrayList<Integer> finalImplicant : finalImplicants){
            piTable.remove(finalImplicant);
        }

        /*
         * Remove all interpretations from the prime implicants chart,
         * which are "covered" by at least one of the final implicants
         */
        Collections.sort(covered);
        Collections.reverse(covered);
        for (int index : covered) {
            for (ArrayList<Integer> primeImplicant : piTable.keySet()) {
                piTable.get(primeImplicant).remove(index);
            }
            interpretationsNumber--;
        }

        /*
         * Add those prime implicants to the set of final implicants
         * which have the most satisfactions by the remaining interpretations.
         */
        while (!piTable.isEmpty()){

            //Identify prime implicant with the most satisfactions by the remaining interpretations.
            int satisfactionMax = 0;
            ArrayList<Integer> finalImp = new ArrayList<>();
            for (ArrayList<Integer> primeImplicant : piTable.keySet()){
                int satisfactionCount = 0;
                ArrayList<Boolean> satisfactions = piTable.get(primeImplicant);
                for (int s = 0; s < satisfactions.size(); s++){
                    if (satisfactions.get(s))
                        satisfactionCount++;
                }
                if ( satisfactionCount > satisfactionMax ){
                    finalImp = primeImplicant;
                    satisfactionMax = satisfactionCount;
                }
            }

            // Remove all the interpretations covered by the new final implicant
            ArrayList<Integer> satisfy = new ArrayList<>();
            for (int i = 0; i < interpretationsNumber; i++) {
                if (piTable.get(finalImp).get(i)){
                    satisfy.add(i);
                }
            }
            Collections.sort(satisfy);
            Collections.reverse(satisfy);
            for (int index : satisfy) {
                for (ArrayList<Integer> primeImplicant : piTable.keySet()) {
                    piTable.get(primeImplicant).remove(index);
                }
                interpretationsNumber--;
            }

            // Remove all the prime implicants which are not satisfied by any of the remaining interpretations
            HashSet<ArrayList<Integer>> remove = new HashSet<>();
            for (ArrayList<Integer> primeImplicant : piTable.keySet()){
                if (!(piTable.get(primeImplicant).contains(true))) {
                    remove.add(primeImplicant);
                }
            }
            for (ArrayList<Integer> rmv : remove) {
                piTable.remove(rmv);
            }

            finalImplicants.add(finalImp);
        }

        return finalImplicants;
    }
}
