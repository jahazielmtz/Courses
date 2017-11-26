function fibonacci(num, memo = {}) {
	if (num < 2) return num;
	if (memo[num]) return memo[num];

	return fibonacci(num - 1, memo) + fibonacci(num - 2, memo);
}