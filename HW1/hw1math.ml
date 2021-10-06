type math_expr = 
  | Const of int
  | Var of string
  | Plus of arg
  | Mult of arg
  | Minus of arg
  | Div of arg
and arg = {arg1: math_expr; arg2: math_expr};;
;;

let rec evaluate e = 
  match e with
  | Const x -> x
  | Var y -> 1
  | Plus {arg1 = e1; arg2 = e2} -> (evaluate e1) + (evaluate e2)
  | Mult {arg1 = e1; arg2 = e2} -> (evaluate e1) * (evaluate e2) 
  | Minus {arg1 = e1; arg2 = e2} -> (evaluate e1) - (evaluate e2) 
  | Div {arg1 = e1; arg2 = e2} -> (evaluate e1) / (evaluate e2);; 
  