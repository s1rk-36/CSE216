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
;;

(* Question 2 *)

let rec compress = function
  | h1 :: (h2 :: _ as t) -> if h1 = h2 then compress t else h1 :: compress t
  | x -> x
;;

(* Question 3 *)

let rec remove_if list func = 
  match list with
  | [] -> []
  | h :: t -> if (func h) then remove_if t func
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
  newList (j - i + 1) (remove i list)
;;
  
(* Question 5 *)

let rec check_equiv list func = 
  match list with
  | [] -> []
  | h :: t -> if (func h) then h :: check_equiv t func
            else check_equiv t func
;;

let rec compare_lists list1 list2 = 
  match list1 with
  | [] -> list2
  | h :: t -> let x = remove_if list2((=)h) in compare_lists t x
;;

let rec equivs func list = 
  match list with
| [] -> [[]]
| h :: t -> let x = check_equiv list (func h) in
let y = (compare_lists x t) in
match y with
| [] -> [x]
| hd::tl -> x :: (equivs func y);;
;;

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


(* Question 7 *)

let rec equiv_on f g lst = 
  match lst with
  | [] -> true
  | h :: t -> if (f h) = (g h) then equiv_on f g t else false 
;;

(* Question 8 *)

let rec pairwisefilter cmp lst = 
  match lst with
  | [] -> []
  | h1 :: h2 :: t -> cmp h1 h2 :: pairwisefilter cmp t 
  | g -> g
;;

(* Question 9 *)

let rec polynomial lst x = 
  match lst with
|[] -> 0
|(coeff, expo) :: t -> pow x expo * coeff + polynomial t x
;;

(* Question 10 *)

let rec map func = function
    [] -> []
  | h :: t -> let r = func h in r :: map func t
  let rec powerset = function
  | [] -> [[]]
  | h :: t -> 
     let ps = powerset t in
     ps @ map (fun g -> h :: g) ps
  ;;
