type arithmetic_expr = 
  | Const of int
  | Plus of arg
  | Mult of arg
  | Minus of arg
  | Div of arg
and arg = {arg1: arithmetic_expr; arg2: arithmetic_expr};;
;;

let rec evaluate e = 
  match e with
  | Const x -> x
  | Plus {arg1 = e1; arg2 = e2} -> (evaluate e1) + (evaluate e2)
  | Mult {arg1 = e1; arg2 = e2} -> (evaluate e1) * (evaluate e2) 
  | Minus {arg1 = e1; arg2 = e2} -> (evaluate e1) - (evaluate e2) 
  | Div {arg1 = e1; arg2 = e2} -> (evaluate e1) / (evaluate e2);; 
let arithmetic_expr = Plus { arg1 = (Mult {arg1 = Const 2; arg2 = Const 3});
                  arg2 = (Mult {arg1 = Const 3; arg2 = (Minus {arg1 = Const 1; 
                                                               arg2 =Const 1})})};;
