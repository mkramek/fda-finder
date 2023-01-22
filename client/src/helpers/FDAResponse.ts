import FDAProduct from "./FDAProduct";

export default interface FDAResponse {
    manufacturer: String,
    products: FDAProduct[]
}