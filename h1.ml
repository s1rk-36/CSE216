(* Question 1 *)
let rec pow x n =
  match n with
  | 0 -> 1
  | 1 -> x
  | _ -> x * pow x (n-1)
;;

let rec float_pow x n = 
  match n with
  | 0 -> 1.0
  | 1 -> x
  | _ -> x *. float_pow x (n-1)
;; (* returns big decimal try: 4.6 and 5*)

float_pow 4.6 5;;

(* Question 2 *)

let rec compress = function
  | a :: (b :: _ as t) -> if a = b then compress t else a :: compress t
  | x -> x
;;


(* Question 3 *)

let rec remove_if list func = 
  match list with
  | [] -> []
  | h::t -> if (func h) then remove_if t func
            else h :: remove_if t func
;;

(* Question 4 *)

let slice list i j =
  let rec newList n = function
    | [] -> []
    | h :: t -> if n = 0 then [] else h :: newList (n - 1) t
  in
  let rec remove n = function
    | [] -> []
    | h :: t  -> if n = 0 then h::t else remove (n - 1) t
  in
  newList (j - i + 1) (remove i list);;

(* Question 5 *)



(* Question 6 *)

let isPrime n =
  let rec countdown m =
    match m with
    | 1 -> true
    | _ -> if n mod m = 0 then false else countdown(m-1)
  in countdown (n -1)
  ;;

let goldbachpair x =
  let rec primeNums y =
    if isPrime y && isPrime (x - y) then (y, x-y) else primeNums(y + 1)
  in
  primeNums 2
;;

(* Question 7*)



(* Question 8*)


(* Question 9*)


(* Question 10 *)