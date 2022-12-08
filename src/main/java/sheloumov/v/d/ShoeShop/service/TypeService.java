package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sheloumov.v.d.ShoeShop.entity.Brand;
import sheloumov.v.d.ShoeShop.entity.Type;
import sheloumov.v.d.ShoeShop.exceptions.NotFoundExceptions;
import sheloumov.v.d.ShoeShop.repository.TypeRepository;

import java.util.List;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type createType(Type type){
        if(typeRepository.findFirstByName(type.getName()) != null){
            Type type1 = typeRepository.findFirstByName(type.getName());
            type1.setName(type.getName());
            typeRepository.save(type1);
            return type1;
        }
        else {
            typeRepository.save(type);
            return type;
        }
    }

    public Type findOneByName(String name){

        Type type = typeRepository.findFirstByName(name);
        if(type != null){
            return type;
        }
        else {
            throw  new NotFoundExceptions();
        }
    }

    public List<Type> getAllType(){
        return typeRepository.findAll();
    }

    public Type deleteItem(Long id){
        Type type = typeRepository.findFirstById(id);
        if(type != null){
            typeRepository.delete(type);
            return type;
        }
        else {
            throw new NotFoundExceptions();
        }
    }
}
