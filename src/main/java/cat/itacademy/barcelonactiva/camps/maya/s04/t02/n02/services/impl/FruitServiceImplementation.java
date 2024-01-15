package cat.itacademy.barcelonactiva.camps.maya.s04.t02.n02.services.impl;

import cat.itacademy.barcelonactiva.camps.maya.s04.t02.n02.domain.Fruit;
import cat.itacademy.barcelonactiva.camps.maya.s04.t02.n02.repository.FruitRepository;
import cat.itacademy.barcelonactiva.camps.maya.s04.t02.n02.services.FruitService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FruitServiceImplementation implements FruitService {
    private final FruitRepository fruitRepo;

    public FruitServiceImplementation(FruitRepository fruitRepo) {
        this.fruitRepo = fruitRepo;
    }

    @Override
    public List<Fruit> getAllFruits() {
        return new ArrayList<Fruit>(fruitRepo.findAll());
    }

    @Override
    public Fruit getFruit(int id) {
        Optional<Fruit> fruit = fruitRepo.findById(id);
        return fruit.orElse(null);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitRepo.save(fruit);
    }

    @Override
    public void updateFruit(int id, Fruit fruit) {
        Optional<Fruit> oldFruit = fruitRepo.findById(id);
        if (oldFruit.isPresent()){
            Fruit updatedFruit = oldFruit.get();
            updatedFruit.setName(fruit.getName());
            updatedFruit.setQuantKilos(fruit.getQuantKilos());
            fruitRepo.save(updatedFruit);
        } else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deleteFruit(int id) {
        Optional<Fruit> fruitToDelete = fruitRepo.findById(id);
        if (fruitToDelete.isPresent()) {
            fruitRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
