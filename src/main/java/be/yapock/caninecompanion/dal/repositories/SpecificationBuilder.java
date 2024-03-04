package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.pl.models.dog.DogSearchForm;
import be.yapock.caninecompanion.pl.models.person.PersonSearchForm;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class SpecificationBuilder {
    public static Specification<Person> specificationBuilder(PersonSearchForm form){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (form.firstName()!=null && !form.firstName().isEmpty())
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),"%" + form.firstName().toLowerCase() + "%"));
            if (form.lastName()!=null && !form.lastName().isEmpty())
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),"%" + form.lastName().toLowerCase() + "%"));
            if (form.phoneNumber()!=null && !form.phoneNumber().isEmpty())
                predicates.add(criteriaBuilder.like(root.get("phoneNumber"), "%" + form.phoneNumber() + "%"));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Dog> specificationBuilderDog(DogSearchForm form){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (form.firstName()!= null && !form.firstName().isEmpty())
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%"+form.firstName().toLowerCase()+ "%"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
