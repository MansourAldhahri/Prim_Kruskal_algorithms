
class Edgee implements Comparable<Edgee> {
        int to, weight,from;

        Edgee(int to, int weight,int from) {
            this.to = to;
            this.weight = weight;
            this.from=from;
        }
        public int getSource() {
        return from;
    }

    public int getDestination() {
        return to;
    }

    public int getWight() {
        return weight;
    }

        @Override
        public int compareTo(Edgee other) {
            return Integer.compare(this.weight, other.weight);
        }
    }