import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Property[] properties = new Property[10];
        Random random = new Random();
        for (int i = 0; i < properties.length; i++) {
            Property newProperty;
            double propertyType = random.nextDouble();
            if (3 * propertyType > 2d) {
                newProperty = new Car(random.nextInt(70000, 5000000), random.nextDouble(0.8d, 10d));
            } else if (propertyType > 1d / 3) {
                newProperty = new Apartment(random.nextInt(3000000, 20000000), "дом Серёжи Кочкина", random.nextInt(5, 200));
            } else {
                int square_h = random.nextInt(10, 500);
                newProperty = new CountryHouse(random.nextInt(1000000, 50000000), square_h, square_h + random.nextInt(10, 100));
            }

            properties[i] = newProperty;
        }

        for (var property : properties) {
            System.out.println(property.CalculateTax());
        }
    }
}