package entities;

public class Charger extends Product {
    private String cableType;
    private String cableLength;

    private Charger(Builder builder) {
        super(builder);
        this.cableType = builder.cableType;
        this.cableLength = builder.cableLength;
    }

    public String getCableType() {
        return cableType;
    }

    public void setCableType(String cableType) {
        this.cableType = cableType;
    }

    public String getCableLength() {
        return cableLength;
    }

    public void setCableLength(String cableLength) {
        this.cableLength = cableLength;
    }

    public static class Builder extends Product.Builder<Builder> {
        private String cableType;
        private String cableLength;

        public Builder setCableType(String cableType) {
            this.cableType = cableType;
            return this;
        }

        public Builder setCableLength(String cableLength) {
            this.cableLength = cableLength;
            return this;
        }

        @Override
        public Charger build() {
            return new Charger(this);
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "Charger{" +
                "cableType='" + cableType + '\'' +
                ", cableLength='" + cableLength + '\'' +
                '}';
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + cableType + "," + cableLength;
    }
}

