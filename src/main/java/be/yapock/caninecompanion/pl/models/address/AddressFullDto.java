package be.yapock.caninecompanion.pl.models.address;

import be.yapock.caninecompanion.dal.models.Address;

public record AddressFullDto(
        int number,
        String box,
        String street,
        int zip,
        String city,
        String country
) {
    public static AddressFullDto fromEntity(Address address){
        return new AddressFullDto(address.getNumber(),
                address.getBox(),
                address.getStreet(),
                address.getZip(),
                address.getCity(),
                address.getCountry());
    }
}
