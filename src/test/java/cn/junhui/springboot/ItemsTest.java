package cn.junhui.springboot;

import cn.junhui.springboot.bean.Items;
import cn.junhui.springboot.service.ItemsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 军辉
 * 2018-10-29 11:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemsTest {

    @Autowired
    private ItemsService itemsService;

    /*
    增加一道试题
     */
    @Test
    public void addItem() {
        Items it = new Items(1, "theme", "A", "B", "C", "D", "B", 5, ": ");
        itemsService.addItem(it);
    }

    /*
    批量增加试题
     */
    @Test
    public void batPapAdd() {
        List<Items> list = new ArrayList<>();
        Integer themeId;
        String theme, op1, op2, op3, op4, currect;
        Items ep;
        for (int i = 31; i < 60; i++) {
            themeId = i + 1;
            ep = new Items(1, "theme" + themeId, "op1" + themeId, "op2" + themeId,
                    "op3" + themeId, "op4" + themeId, "currect" + themeId, +themeId + themeId, "sda");
            list.add(ep);
        }
        itemsService.batPapAdd(list);
    }

    /*
    批量删除试题（根据题号）
     */
    @Test
    public void batPapDel() {
        int paperId = 1;
        List<Items> list = new ArrayList<>();
        Items ep;
        for (int i = 21; i < 40; i++) {
            ep = itemsService.selPap(paperId, i + 1);
            list.add(ep);
        }
        itemsService.batPapDel(list);
       /* for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }*/
    }

    /*
    多条件查询试题
     */
    @Test
    public void selPap() {
        Items ep = itemsService.selPap(1, 25);
        System.out.println(ep);
    }

    /*
    查询
     */
    @Test
    public void getall() {
        List<Items> list = itemsService.getAllItems();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    /*
    修改试题+
     */
    @Test
    public void update() {
        Items items = itemsService.selPap(1, 77);
        items.setOp1("sdasdasad");
        itemsService.dynamicItemUdp(items);
    }

    /*
    删除试题
     */
    @Test
    public void delete() {
        Items items = itemsService.selPap(1, 83);

        itemsService.deleteItem(items);
    }

    /*
    根据paperId查询试题
     */
    @Test
    public void getByPaperId() {
        List<Items> list = itemsService.getallByPaperId(3);
        System.out.println(list);
    }

}
