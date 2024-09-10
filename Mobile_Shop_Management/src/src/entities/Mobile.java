package entities;

public class Mobile extends Product {
    private String os;

    private Mobile(Builder builder) {
        super(builder);
        this.os = builder.os;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public static class Builder extends Product.Builder<Builder> {
        private String os;

        public Builder setOs(String os) {
            this.os = os;
            return this;
        }

        @Override
        public Mobile build() {
            return new Mobile(this);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Mobile{" + "os=" + os + '}';
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + os;
    }
}
