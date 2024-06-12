package cool;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.core.statement.Query;
import java.io.Serializable;

public class JdBIDuckDb {


    public static class Item implements Serializable {

        private static final long serialVersionUID = 1L;

        private String item;
        private float value;
        private int count;

        // No-argument constructor
        public Item() {
        }

        // Constructor with arguments
        public Item(String item, float value, int count) {
            this.item = item;
            this.value = value;
            this.count = count;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        // Getter and Setter for value
        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        // Getter and Setter for count
        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "InventoryItem{" +
                    "item='" + item + '\'' +
                    ", value=" + value +
                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:duckdb:");
        jdbi.useHandle(handle -> {
            handle.execute(DuckDbExplorations.CREATE_TABLE);
            handle.execute(DuckDbExplorations.INSERT_INTO_TABLE);

            Query query = handle.createQuery(DuckDbExplorations.SELECT);
            ResultIterable<Item> results = query.mapToBean(Item.class);

            for(Item item : results) {
                System.out.println(item);
            }

        });

    }
}
