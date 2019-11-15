import { MenuItem } from "src/app/food/food.model";

export interface Cart{
    // items:[{
    //     itemId:string,
    //     product:MenuItem
    //     quantity?:number
    // }]
    // total:number

    menuItemList:MenuItem[]
    total:number
}