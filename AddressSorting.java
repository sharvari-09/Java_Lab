import java.util.*;

class Address implements Comparable<Address> {
    private String streetName;
    private String city;
    private String pincode;

    public Address(String streetName, String city, String pincode) {
        this.streetName = streetName;
        this.city = city;
        this.pincode = pincode;
    }

    @Override
    public int compareTo(Address other) {
        // Compare pincode first
        int pincodeCompare = this.pincode.compareTo(other.pincode);
        if (pincodeCompare != 0) {
            return pincodeCompare;
        }
        // If pincode is the same, compare street name
        return this.streetName.compareTo(other.streetName);
    }

    @Override
    public String toString() {
        return "Street Name: " + streetName + ", City: " + city + ", Pincode: " + pincode;
    }
}

public class AddressSorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Address> addresses = new ArrayList<>();

        // Take input for addresses
        System.out.println("Enter addresses in the format (Street Name, City, Pincode), enter 'done' to finish:");
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("done")) {
            String[] parts = input.split(",");
            if (parts.length == 3) {
                String streetName = parts[0].trim();
                String city = parts[1].trim();
                String pincode = parts[2].trim();
                addresses.add(new Address(streetName, city, pincode));
            } else {
                System.out.println("Invalid input format. Please enter again.");
            }
        }

        // Sort the addresses
        Collections.sort(addresses);

        // Display the sorted addresses
        System.out.println("Sorted Addresses:");
        for (Address address : addresses) {
            System.out.println(address);
        }
    }
}
