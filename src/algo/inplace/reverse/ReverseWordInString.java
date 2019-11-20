package algo.inplace.reverse;

public class ReverseWordInString {

    public static void main(String[] args) {
        char delimiter = ' ';
        char[] input =
                { 'p', 'e', 'r', 'f', 'e', 'c', 't', delimiter,
                  'm', 'a', 'k', 'e', 's', delimiter,
                  'm', 'a', 'k', 'e', 's', delimiter,
                  'p', 'r', 'a', 'c', 't', 'i', 'c', 'e' };

        System.out.println(input);

        int first = 0;
        int last = input.length - 1;
        int current = first;
        boolean found;

        do {
            found = false;
            for (; current <= last; current++) {
                if (input[current] == delimiter) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                break;
            }

            for (int j = current; j >= first; j--) {

                char tmp = input[j];

                for (int i = j; i < last; i++) {
                    input[i] = input[i + 1];
                }
                input[last] = tmp;
                if (tmp != delimiter) {
                    last--;
                }

                System.out.println(input);
            }
            current = first;
            last--;
        } while (true);
    }

}
