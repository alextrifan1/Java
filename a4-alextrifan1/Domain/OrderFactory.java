package Domain;

import Repository.DuplicateEntityException;
import Repository.FileRepository;
import Repository.IRepository;
import Repository.RepositoryException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class OrderFactory implements IEntityFactory<Order>{
    @Override
    public Order createEnity(String line) throws DuplicateEntityException, IOException, RepositoryException {

        /// poate nu e chiar bine aici
        IEntityFactory<Cake> cakeFactory = new CakeFactory();
        IRepository<Cake> cakeRepository = new FileRepository<>("C:\\Users\\alexandru\\Documents\\GitHub\\a4-alextrifan1\\cakes.txt", cakeFactory);

        int id = Integer.parseInt(line.split(",")[0]);
        String ordersIdList = line.split(",")[1];

        String[] orderIdListForAdding = ordersIdList.split(" ");
        ArrayList<Cake> cakes = new ArrayList<>();
        for (String ordId: orderIdListForAdding) {
            int idOrd = Integer.parseInt(ordId);
            Cake cake = cakeRepository.findById(idOrd);
            cakes.add(cake);
        }
        String date = line.split(",")[2];
        return new Order(id, cakes, date);
    }

    @Override
    public String toString(Order entity) {
        return entity.toString();
    }
}