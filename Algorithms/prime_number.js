function primeNumber(num) {
	let isPrime = true;
	let res = 0;
	for (let x = 2; true; x++, count = 0) {
		for (let y = x-1; y > 1; y--) {
			if (x % y == 0) {
				isPrime = false;
			}
		}
		if (isPrime) {
			res++;
		}
		if (res == num) {
			return x;
		}
	}
}