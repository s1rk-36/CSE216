type bool_expr =
| Lit of string
| Not of bool_expr
| And of bool_expr * bool_expr
| Or of bool_expr * bool_expr
;;

let rec truth_values a truth_a b truth_b = function
| Lit x -> if x = a then truth_a
            else if x = b then truth_b
            else failwith "Invalid args"
| Not e -> not (truth_values a truth_a b truth_b e)
| And(e1, e2) -> truth_values a truth_a b truth_b e1 && truth_values a truth_a b truth_b e2
| Or(e1, e2) -> truth_values a truth_a b truth_b e1 || truth_values a truth_a b truth_b e2
let truth_table a b c =
  [(true,  true,  truth_values a true  b true  c);
     (true,  false, truth_values a true  b false c);
     (false, true,  truth_values a false b true  c);
     (false, false, truth_values a false b false c)];;
